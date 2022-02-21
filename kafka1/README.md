bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic LOJA_NOVOPEDIDO

bin/kafka-topics.sh --list --bootstrap-server localhost:9092

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic LOJA_NOVOPEDIDO

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic LOJA_NOVOPEDIDO

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic LOJA_NOVOPEDIDO --from-beginning
