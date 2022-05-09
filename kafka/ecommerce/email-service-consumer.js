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
            try {
                const { email, order } = JSON.parse(payload.message.value);
                if (!email || !order) {
                    console.log(`Failed to sent email for this order`, order)
                    return;
                }
                console.log('=========================================')
                console.log(`enviando email para: ${order.user_email} => ${email.subject}`);
                console.log(`Email Enviado!`)
            } catch (err) {
                console.log(`Failed to load message to sent by email, trying next one`, payload.message)
            }
        }
    })
}

run();