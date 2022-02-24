const { Kafka } = require('kafkajs')
const crypto  = require('crypto');

const kafka = new Kafka({
    clientId: 'my-app',
    brokers: ['localhost:9092']
})

const producer = kafka.producer()
const topic = 'ECOMMERCE_NEW_ORDER'
const run = async () => {
  // Producing
    await producer.connect()
    const value = JSON.stringify({ user_id: crypto.randomBytes(10).toString('hex'), items: []  })
    for(let i = 0; i < 10; i++) {
        await producer.send({
            topic,
            messages: [
                { value, key: 'Order-' + crypto.randomBytes(10).toString('hex')},
            ],
        }).then((result) =>{
            console.log(`Message sent! 
            topic: ${topic}
            partition: ${result[0].partition}
            startOffeset: ${result[0].logStartOffset}
            Baseoffset: ${result[0].baseOffset}
            time: ${result[0].timestamp}`)
        }).catch((err) =>{
            console.log('Failed sending message: ' + err);
        });
    }

    const email = 'Welcome we are processing your order!'
    await producer.send({
        topic: 'ECOMMERCE_SEND_EMAIL',
        messages: [
            { value: email },
        ],
    }).then((result) =>{
        console.log(`Message sent! 
        topic: ${topic}
        partition: ${result[0].partition}
        startOffeset: ${result[0].logStartOffset}
        Baseoffset: ${result[0].baseOffset}
        time: ${result[0].timestamp}`)
    }).catch((err) =>{
        console.log('Failed sending message: ' + err);
    });
}

run();