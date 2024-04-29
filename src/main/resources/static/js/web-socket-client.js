// WebSocketClient.js
class WebSocketClient {
    constructor() {
        if (!WebSocketClient.instance) {

            const protocol = window.location.protocol;
            const host = window.location.host;
            const url = protocol + '//' + host + '/chat';
            const headers = {
                'authorization': 'Bearer token',
                'username': 'username',
                'role': 'admin',
            }
            this.url = url;
            this.headers = headers;
            this.socket = new SockJS(this.url);
            this.client = Stomp.over(this.socket);
            this.client.debug = null;
            WebSocketClient.instance = this;
        }
        return WebSocketClient.instance;
    }

    connect() {
        this.client.connect(this.headers, this.onConnected, this.onError);
    }

    updateUsername(username) {
        console.log("username: ", username)
        this.headers['username'] = username;
    }

    connectWithCallback(onConnected) {
        console.log("headers: ", this.headers)
        this.client.connect(this.headers, (frame) => {
            onConnected(frame);
        }, this.onError);
    }

    subscribe(destination, callback) {
        this.client.subscribe(destination, message => {
            callback(message);
        });
    }

    send(destination, message, headers = {}) {
        this.client.send(destination, headers, message);
    }

    disconnect() {
        if (this.client !== null) {
            this.client.disconnect();
        }
        console.log("Disconnected");
    }

    onConnected(frame) {
        console.log('Connected: ' + frame);
        window.sessionId = frame.headers["user-name"];
    }

    onError(error) {
        console.log('Error: ' + error);
    }
}

const instance = new WebSocketClient();
Object.freeze(instance);

export default instance;
