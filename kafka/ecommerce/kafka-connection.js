const { Kafka } = require('kafkajs')

/**
 * 
 * @returns {Kafka}
 */
const connect = () => {
    return new Kafka({
        clientId: 'my-app',
        brokers: ['localhost:9092']
    });
}

module.exports = connect;
