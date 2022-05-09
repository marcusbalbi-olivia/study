const { Kafka } = require('kafkajs')
const fs = require('fs');
const crypto = require('crypto');

const kafka = new Kafka({
    clientId: 'my-app',
    brokers: ['localhost:9092']
})
const consumer = kafka.consumer({ groupId: 'SERVICE_USERS' })

const openDB = () => {
    if (!fs.existsSync('./db.json')) {
        fs.writeFileSync('./db.json', JSON.stringify({ users: [] }));
    }
    return require('./db.json');
}

const saveDB = (data) => {
    fs.writeFileSync('./db.json', JSON.stringify(data, " ", 2));
}

/**
 * 
 * @param {*} order 
 * @returns true if user exists
 */
const checkUserExists = (order) => {
    const db = openDB();

    if (!db.users || !Array.isArray(db.users) || db.users.length === 0) {
        return false;
    }
    return db.users.some(user => user.email === order.user_email);
}

const saveNewUser = (order) => {
    const db = openDB();
    if (!db.users) { db.users = []; }
    const user = {
        email: order.user_email,
        id: crypto.randomBytes(10).toString('hex')
    }
    db.users.push(user);
    saveDB(db);
}

const checkAndSaveUser = (order) => {
    if (checkUserExists(order)) {
        console.log('User already exists, nothing to do');
    } else {
        console.log('New user registered: ', order.user_email);
        saveNewUser(order);
    }
}

const run = async () => {
    await consumer.connect();
    consumer.subscribe({ topic: "ECOMMERCE_NEW_ORDER" });
    await consumer.run({
        eachMessage: (payload) => {
            const order = JSON.parse(payload.message.value);
            console.log('=========================================')
            if (!order.user_email) {
                console.log('Email do Usuáro não enviado no pedido', order)
                return;
            }
            console.log(`registrando usuário: ${order.user_email}`);
            checkAndSaveUser(order);
        }
    })
}

run();