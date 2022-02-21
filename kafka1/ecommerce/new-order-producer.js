const { Kafka } = require('kafkajs')

const kafka = new Kafka({
    clientId: 'my-app',
    brokers: ['localhost:9092']
})

const producer = kafka.producer()
const topic = 'ECOMMERCE_NEW_ORDER'
const run = async () => {
  // Producing
    await producer.connect()
    const value = "12345,6654,3500"
    await producer.send({
        topic,
        messages: [
            { value },
        ],
    }).then((result) =>{
        console.log(`Message sent! 
        topic: ${topic}
        partition: ${result[0].partition}
        startOffeset: ${result[0].logStartOffset}
        Baseoffset: ${result[0].baseOffset}
        time: ${result[0].timestamp}`)
    }).catch((err) =>{
        console.log('Failed sending message: ' + err);
    });
}

run();