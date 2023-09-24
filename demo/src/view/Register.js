import React, { useState } from "react";
import {Form, Input, Button} from "antd";

const Register = () => {
    const [password, setPassword] = useState("");

    const onFinish = (values) => {
        const { username, confirmPassword, email, phone, address, avatar } = values;
        // 确认密码
        if(password !== confirmPassword){
            alert('两次密码不一致');
            return;
        }

        fetch('http://localhost:8080/register', {
            credentials: 'include',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({ username: username, password: password, email: email, phone: phone, address: address, avatar: avatar}),
        })
            .then(response => {
                return response.json();
            })
            .then((data) => {
                    console.log(data);
                    if(data !== false){
                        alert('注册成功');
                    }
                    else{
                        alert('注册失败，该用户名已被注册');
                    }
                }
            )
            .catch((error) => {
                    console.log(error);
                }
            );
        
    };

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    }

    return (
        <div className="register">
            <div className="register-content">
                <Form
                    id="basic"
                    labelCol={{
                        span: 8,
                    }}
                    wrapperCol={{
                        span: 16,
                    }}
                    style={{
                        maxWidth: 600,
                    }}
                    initialValues={{
                        remember: true,
                    }}
                    onFinish={onFinish}
                    onFinishFailed={onFinishFailed}
                >
                    <Form.Item
                        label="用户名"
                        name="username"
                        rules={[
                            {
                                required: true,
                                message: '用户名不能为空!',
                            },
                        ]}
                    >
                        <Input type="text" />
                    </Form.Item>
                    <Form.Item
                        label="密码"
                        name="password"
                        rules={[
                            {
                                required: true,
                                message: '密码不能为空!',
                            },
                        ]}
                    >
                        <Input.Password onChange={handlePasswordChange} />
                    </Form.Item>
                    <Form.Item
                        label="确认密码"
                        name="confirmPassword"
                        rules={[
                            {
                                required: true,
                                message: '密码不能为空!',
                            },
                            {
                                validator: (_, value) =>
                                    value === password ? Promise.resolve() : Promise.reject('两次密码不一致!'),
                            },
                        ]}
                    >
                        <Input.Password />
                    </Form.Item>
                    <Form.Item
                        label="邮箱"
                        name="email"
                        rules={[
                            {
                                required: true,
                                message: '邮箱不能为空!',
                            },
                        ]}
                    >
                        <Input type="email" />
                    </Form.Item>
                    <Form.Item
                        label="手机号"
                        name="phone"
                        rules={[
                            {
                                required: true,
                                message: '手机号不能为空!',
                            },
                        ]}
                    >
                        <Input type="tel" />
                    </Form.Item>
                    <Form.Item
                        label="地址"
                        name="address"
                        rules={[
                            {
                                required: true,
                                message: '地址不能为空!',
                            },
                        ]}
                    >
                        <Input type="text" />
                    </Form.Item>
                    <Form.Item
                        label="头像"
                        name="avatar"
                        rules={[
                            {
                                required: true,
                                message: '头像不能为空!',
                            },
                        ]}
                    >
                        <Input type="text" />
                    </Form.Item>
                    <Form.Item
                        wrapperCol={{
                        offset: 8,
                        span: 16,
                        }}
                    >
                        <Button type="primary" htmlType='submit' style={{margin:20}} >
                            注册
                        </Button>
                        <Button type="link" href="/" style={{margin:20}} >
                            取消
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        </div>
    );
};

export default Register;
