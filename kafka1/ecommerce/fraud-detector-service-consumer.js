const { Kafka } = require('kafkajs')

const kafka = new Kafka({
    clientId: 'my-app',
    brokers: ['localhost:9092']
})
const consumer = kafka.consumer({ groupId: 'FRAUD_DETECTOR' })

const run = async () => {
    await consumer.connect();
    consumer.subscribe({ topic: "ECOMMERCE_NEW_ORDER" });
    await consumer.run({
        eachMessage: (payload) => {
            console.log('===========================')
            console.log(`Lendo da partiçaão: ${payload.partition}`)
            console.log(`procurando fraude no pedido: ${payload.message.key}`);
            console.log(`Pedido Processado!`)
        }
    })
}

run();