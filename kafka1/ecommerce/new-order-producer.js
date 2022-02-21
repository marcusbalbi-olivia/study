const { Kafka } = require('kafkajs')

const kafka = new Kafka({
    clientId: 'my-app',
    brokers: ['localhost:9092']
})

const producer = kafka.producer()
const consumer = kafka.consumer({ groupId: 'test-group' })

const run = async () => {
  // Producing
    await producer.connect()
    const value = "12345,6654,3500"
    await producer.send({
        topic: 'ECOMMERCE_NEW_ORDER',
        messages: [
            { value },
        ],
    });
}

run();