const { Kafka } = require('kafkajs')

const kafka = new Kafka({
    clientId: 'my-app',
    brokers: ['localhost:9092']
})
const consumer = kafka.consumer({ groupId: 'FRAUD_DETECTOR' })
const producer = kafka.producer()
const wait = (ms) => {
    return new Promise((resolve, _) => {
        setTimeout(() => { resolve(true) }, ms);
    })
}

const isFraud = (order) => {
    return parseInt(order.amount) > 4500
}

const run = async () => {
    await consumer.connect();
    await producer.connect();
    consumer.subscribe({ topic: "ECOMMERCE_NEW_ORDER" });
    await consumer.run({
        eachMessage: async (payload) => {
            const order = JSON.parse(payload.message.value);
            console.log('===========================')
            console.log(`Lendo da partição: ${payload.partition}`)
            console.log(`procurando fraude no pedido: ${payload.message.key}`);
            console.log(`Analisando o pedido: Usuario: ${order.user_email}, Numero do Pedido: ${order.order_id}, valor: ${order.amount}`)
            await wait(1000);

            if (isFraud(order)) {
                // pretend that the fraud happens when the amount is higher than 4500
                console.log(`Pedido é uma Fraude!`, order);
                producer.send({
                    topic: 'ECOMMERCE_ORDER_REJECTED',
                    messages: [
                        { value: JSON.stringify(order), key: order.user_email },
                    ],
                })
            } else {
                console.log(`Pedido Aprovado!`, order);
                producer.send({
                    topic: 'ECOMMERCE_ORDER_APPROVED',
                    messages: [
                        { value: JSON.stringify(order), key: order.user_email },
                    ],
                })
            }

        }
    })
}

run();