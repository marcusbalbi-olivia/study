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
            const order = JSON.parse(payload.message.value);
            console.log('===========================')
            console.log(`Lendo da partição: ${payload.partition}`)
            console.log(`procurando fraude no pedido: ${payload.message.key}`);
            console.log(`Analisando o pedido: Usuario: ${order.user_id}, Numero do Pedido: ${order.order_id}, valor: ${order.amount}`)
            console.log(`Pedido Processado!`)
        }
    })
}

run();