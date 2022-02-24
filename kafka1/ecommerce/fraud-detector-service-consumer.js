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
            console.log('===================================================================')
            console.log(`Processando mensagem: ${payload.message.key}, procurando por fraude`);
            console.log(`Pedido Processado!`)
            console.log('===================================================================')
        }
    })
}

run();