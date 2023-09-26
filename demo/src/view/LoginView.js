import React from 'react';
import '../css/index.css';
import { Button, Checkbox, Form, Input, Modal } from 'antd';

export default function LoginView({ onLoginSuccess }) {
    const onFinish = (values) => {
      const { username, password } = values;
      fetch('http://localhost:8080/login', {
        method: 'POST',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json'
        },
        body: JSON.stringify({ user: username, pwd: password }),
      })
        .then(response => {
          return response.json();
        })
        .then((data) => {
          console.log(data.data);
          if(data.status === 1){
            if(data.data.mode === "ban"){
              Modal.error({
                title: '该账户已被禁用，请联系管理员'
            });
              return;
            }
            localStorage.setItem('uid', data.data.uid);
            localStorage.setItem('username', data.data.username);
            localStorage.setItem('address', data.data.address);
            localStorage.setItem('phone', data.data.phone);
            localStorage.setItem('email', data.data.email);
            localStorage.setItem('avatar', data.data.avatar);
            localStorage.setItem('mode', data.data.mode);
            window.location.href = "/Home";
          }
          else{
            Modal.error({
              title: '登录失败',
              content: data.message,
            });
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

  return (
    <div className="login">
      <div className="login-content">
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
            <Input.Password type="password" />
          </Form.Item>

          <Form.Item
            wrapperCol={{
              offset: 8,
              span: 16,
            }}
          >
            <Button type="primary" htmlType="submit">
              登录
            </Button>
            <Button type="link" href="/register" htmlType='submit' style={{margin:20}} >
              注册
            </Button>
          </Form.Item>
        </Form>
      </div>
    </div>
  );
}
