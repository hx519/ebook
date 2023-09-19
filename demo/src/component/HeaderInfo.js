import React from "react";
import { Row, Col, Link, Button } from "antd";
import "../css/index.css"
import logo from "../assets/logo.jpg";
import bookstore from "../assets/bookstore.png";
import avatar from "../assets/avatar.jpg";


export class HeaderInfo extends React.Component {

    render() {
        return(
            <div id="header">
                <div id="header-content">
                    <Row>
                        <Col xs={24} sm={24} md={5} lg={5} xl={5} xxl={4}>
                            <a id="logo" href={"/"}>
                                <img alt="logo"  className="logo" src={logo} style={{ height:45 }}/>
                                <img alt="bookstore"  className="bookstore" src={bookstore} style={{ height:45 }}/>
                                {/* <img id="avatar" alt="avatar"  className="avatar" src={avatar} style={{ height:45 }}/> */}
                                <Button 
                                    type="primary" 
                                    style={{
                                        marginLeft: "780px",
                                        marginTop: "0px",
                                        height: "35px",
                                        width: "60px",
                                            }}
                                    onClick={() => {
                                        localStorage.removeItem('mode');
                                        localStorage.removeItem('username');
                                        localStorage.removeItem('uid');
                                        localStorage.removeItem('email');
                                        localStorage.removeItem('phone');
                                        localStorage.removeItem('address');
                                        localStorage.removeItem('avatar');
                                        window.location.href = "/";
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
