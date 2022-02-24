const { Kafka } = require('kafkajs')

const kafka = new Kafka({
    clientId: 'my-app',
    brokers: ['localhost:9092']
})
const consumer = kafka.consumer({ groupId: 'EMAIL_SERVICE' })

const run = async () => {
    await consumer.connect();
    consumer.subscribe({ topic: "ECOMMERCE_SEND_EMAIL" });
    await consumer.run({
        eachMessage: (payload) => {
            console.log('=========================================')
            console.log(`enviando email: ${payload.message.value}`);
            console.log(`Email Enviado!`)
        }
    })
}

run();