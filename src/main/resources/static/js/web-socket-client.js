class WebSocketClient {
    constructor(url, headers) {
        this.url = url;
        this.headers = headers;
        this.socket = new SockJS(this.url);
        this.client = Stomp.over(this.socket);
        this.client.debug = null;
    }

    connect(onConnectedCallback, onErrorCallback) {
        this.client.connect(this.headers, function (frame) {
            onConnectedCallback(frame);
        }, function (error) {
            onErrorCallback(error);
        });
    }

    subscribe(destination, callback) {
        this.client.subscribe(destination, function (message) {
            callback(message);
        });
    }

    send(destination, message) {
        this.client.send(destination, {}, message);
    }

    disconnect() {
        if (this.client !== null) {
            this.client.disconnect();
        }
        console.log("Disconnected");
    }
}


