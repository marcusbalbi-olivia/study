const { Kafka } = require('kafkajs')

const kafka = new Kafka({
    clientId: 'my-app',
    brokers: ['localhost:9092']
})
const consumer = kafka.consumer({ groupId: 'LOG_SERVICE' })

const run = async () => {
    await consumer.connect();
    await consumer.subscribe({ topic: /ECOMMERCE.*/i }).catch((err) => console.log(`Failed to subscribe log`));
    await consumer.run({
        eachMessage: (payload) => {
            console.log('======LOG START====')
            console.log(`Topic: ${payload.topic}`)
            console.log(`Value: ${payload.message.value.toString()}`)
            console.log('======LOG END====')
        }
    })
}

run();