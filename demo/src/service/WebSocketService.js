import { Modal, message } from 'antd';

export const createWebSocket = (userId) => {
    // 创建WebSocket连接
    const socket = new WebSocket('ws://localhost:8080/websocket/' + userId);

    socket.onopen = () => {
        console.log('WebSocket连接已建立');
        // 发送测试请求给后端
        const message = { type: 'testRequest', content: '测试请求' };
        socket.send(JSON.stringify(message));
    };

    socket.onclose = () => {
        console.log('WebSocket连接已关闭');
    }

    socket.onmessage = (event) => {
        const receivedMessage = JSON.parse(event.data);
        console.log('收到来自服务器的:', receivedMessage);
        if(receivedMessage.status === -1){
            Modal.error({
                title: '订单提交失败',
                content: receivedMessage.message,
            });
            socket.close();
            return;
        }
        Modal.success({
            title: '订单提交成功',
            content: '订单时间：' + receivedMessage.time + '\n 订单总价：' + receivedMessage.price + '元'
        });
        socket.close();
    };
};
