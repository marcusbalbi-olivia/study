const express = require('express');
const crypto = require('crypto');
const { Kafka } = require('kafkajs')
const port = 3000

const kafka = new Kafka({
    clientId: 'my-app',
    brokers: ['localhost:9092']
});
const producer = kafka.producer();

const sendOrderKafka = (order) => {
    const topic = 'ECOMMERCE_NEW_ORDER'
    /**
     * acks	Control the number of required acks.
            -1 = all insync replicas must acknowledge (default)
            0 = no acknowledgments
            1 = only waits for the leader to acknowledge	-1 all insync replicas must acknowledge
     */
    return producer.send({
        topic,
        acks: 1,
        messages: [
            { value: JSON.stringify(order), key: order.user_email },
        ],
    });
}

const sendOrderEmailKafka = (order) => {
    const email = {
        subject: 'Welcome we are processing your order!',
        body: 'Welcome we are processing your order!',
    }
    return producer.send({
        topic: 'ECOMMERCE_SEND_EMAIL',
        messages: [
            { value: JSON.stringify({ order, email }) },
        ],
    });
}

const logMessage = (result) => {
    console.log(`Message sent!
            topic: ${result[0].topicName}
            partition: ${result[0].partition}
            startOffeset: ${result[0].logStartOffset}
            Baseoffset: ${result[0].baseOffset}
            time: ${result[0].timestamp}`);
}

const app = express();

app.use(express.json());

app.post('/new-order', async (req, res) => {
    // get from body create new order
    const userEmail = req.body.email;
    const items = req.body.items;
    const total = items
        .map((item) => item.value * item.quantity)
        .reduce((prev, curr) => prev + curr, 0)

    const order = {
        user_email: userEmail,
        order_id: 'Order-' + crypto.randomBytes(10).toString('hex'),
        amount: total,
        items
    }
    console.log(`Order created:`, order);
    // produce message
    await sendOrderKafka(order)
    .then(logMessage)
    .catch((err) => {
        console.log('Failed sending message: ' + err);
    });
    await sendOrderEmailKafka(order)
    .then(logMessage)
    .catch((err) => {
        console.log('Failed sending message: ' + err);
    });
    res.send({ status: true, order });
})

app.listen(port, async () => {
    await producer.connect();
    console.log(`Listing:: ${port}`)
})