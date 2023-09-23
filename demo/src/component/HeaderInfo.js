import React, { useEffect, useState } from "react";
import { Row, Col, Button } from "antd";
import "../css/index.css";
import logo from "../assets/logo.jpg";
import bookstore from "../assets/bookstore.png";
import avatar from "../assets/avatar.jpg";

export class HeaderInfo extends React.Component {
  constructor(props) {
    super(props);
  }

  handleLogout = () => {
    console.log("logout");
    // 向后端发送一个注销请求
    fetch("http://localhost:8080/logout", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if (data.status >= 0) {
          // 成功注销，清除本地存储并重定向
          localStorage.removeItem("mode");
          localStorage.removeItem("username");
          localStorage.removeItem("uid");
          localStorage.removeItem("email");
          localStorage.removeItem("phone");
          localStorage.removeItem("address");
          localStorage.removeItem("avatar");
          console.log("注销成功");
          window.location.href = "/";
          alert("当前用户登录时长：" + data.data + "ms");
        } else {
          // 处理注销失败或来自服务器的错误
          console.error("注销失败");
        }
      })
      .catch((error) => {
        console.error("注销期间发生错误：", error);
      });
  };

  render() {
    return (
      <div id="header">
        <div id="header-content">
          <Row>
            <Col xs={24} sm={24} md={5} lg={5} xl={5} xxl={4}>
              <a id="logo" >
                <img alt="logo" className="logo" src={logo} style={{ height: 45 }} />
                <img alt="bookstore" className="bookstore" src={bookstore} style={{ height: 45 }} />
                <Button
                  type="primary"
                  style={{
                    marginLeft: "780px",
                    marginTop: "0px",
                    height: "35px",
                    width: "60px",
                  }}
                  onClick={() => {
                    this.handleLogout();
                  }}
                >
                  登出
                </Button>
              </a>
            </Col>
          </Row>
        </div>
      </div>
    );
  }
}
