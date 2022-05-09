const { Kafka } = require('kafkajs')
const crypto  = require('crypto');

const kafka = new Kafka({
    clientId: 'my-app',
    brokers: ['localhost:9092']
}); 

const producer = kafka.producer()
const topic = 'ECOMMERCE_NEW_ORDER'
const run = async () => {
  // Producing
    await producer.connect();

    const email = {
        subject: 'Welcome we are processing your order!',
        body: 'Welcome we are processing your order!',
    }

    const orders = [];

    for(let i = 0; i < 10; i++) {
        orders.push({ 
            user_email: crypto.randomBytes(10).toString('hex') + '@example.com',
            order_id: 'Order-' + crypto.randomBytes(10).toString('hex'),
            amount: Math.random() * 5000 + 1
        });
    }

    for(const order of orders) {
        await producer.send({
            topic,
            messages: [
                { value: JSON.stringify(order), key: order.user_email },
            ],
        }).then((result) => {
            console.log(`Message sent! 
            topic: ${topic}
            partition: ${result[0].partition}
            startOffeset: ${result[0].logStartOffset}
            Baseoffset: ${result[0].baseOffset}
            time: ${result[0].timestamp}`)
        }).catch((err) => {
            console.log('Failed sending message: ' + err);
        });


        await producer.send({
            topic: 'ECOMMERCE_SEND_EMAIL',
            messages: [
                { value: JSON.stringify({ order, email }) },
            ],
        }).then((result) => {
            console.log(`Message sent! 
        topic: ${topic}
        partition: ${result[0].partition}
        startOffeset: ${result[0].logStartOffset}
        Baseoffset: ${result[0].baseOffset}
        time: ${result[0].timestamp}`)
        }).catch((err) => {
            console.log('Failed sending message: ' + err);
        });
    }
}

run();