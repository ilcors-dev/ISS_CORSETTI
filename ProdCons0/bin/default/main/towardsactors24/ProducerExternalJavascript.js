const net = require('net');

class TCPClient {
	constructor(host, port) {
		this.host = host;
		this.port = port;
		this.client = new net.Socket();
		this.setupClient();
	}

	setupClient() {
		this.client.on('data', (data) => {
			console.log('Received reply:', data.toString().trim());
		});

		this.client.on('close', () => {
			console.log('Connection closed');
		});

		this.client.on('error', (err) => {
			console.error('Connection error:', err);
		});
	}

	connect() {
		this.client.connect(this.port, this.host, () => {
			console.log(`Connected to ${this.host}:${this.port}`);
		});
	}

	send(message) {
		console.log('Sending:', message);
		this.client.write(`${message}\n`);
	}

	terminate() {
		this.client.end(() => {
			console.log('Disconnected from the server.');
		});
	}
}

function doJob(client) {
	const messages = [
		'msg(distance,request, prodpython,consumer,33,1)',
		'msg(distance,dispatch,prodpython,consumer,22,2)',
		'msg(distance,request, prodpython,consumer,43,1)',
		'msg(distance,dispatch,prodpython,consumer,22,2)',
	];

	messages.forEach((message, index) => {
		setTimeout(() => {
			client.send(message);
		}, 500 * index); // sending messages with a delay
	});

	setTimeout(() => {
		client.terminate();
	}, 500 * messages.length + 1000); // ensure all messages are sent before terminating
}

const host = 'localhost';
const port = 8223;
const tcpClient = new TCPClient(host, port);

tcpClient.connect();
doJob(tcpClient);
