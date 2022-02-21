const { Kafka } = require('kafkajs')

const kafka = new Kafka({
    clientId: 'my-app',
    brokers: ['localhost:9092']
})
const consumer = kafka.consumer({ groupId: 'test-group' })

const run = async () => {
    await consumer.connect();
    consumer.subscribe({ topic: "ECOMMERCE_NEW_ORDER" })
    await consumer.run({
        
    })
}

run();