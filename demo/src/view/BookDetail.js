import React from 'react';
import { Descriptions, Button, Form, Input, Modal } from 'antd';
import { Link } from 'react-router-dom';
import { useLocation } from 'react-router-dom';
import '../css/index.css';
import { booksOnCart } from '../localStorage';
import {IdcardOutlined, ShoppingCartOutlined, HomeOutlined, UnorderedListOutlined,LineChartOutlined } from '@ant-design/icons';
import { Layout, theme, Menu } from 'antd';
import {HeaderInfo} from "../component/HeaderInfo";
import { useNavigate, Routes, Route, Outlet } from 'react-router-dom';
import { useState } from 'react';

const { Header, Content, Sider } = Layout;

export function BookDetail (props) {   
        const items = localStorage.getItem('user') && localStorage.getItem('mode') === 'admin' ? [
            {
                key:'BookAdmin',
                icon: <UnorderedListOutlined />,
                children: null,
                label: '图书管理',
                path: '/admin/books',
            },
            {
                key:'OrderAdmin',
                icon: <ShoppingCartOutlined />,
                children: null,
                label: '订单管理',
                path: '/admin/orders',
            },
            {
                key:'UserAdmin',
                icon: <IdcardOutlined />,
                children: null,
                label: '用户管理',
                path: '/admin/users',
            },
            {
                key:'Statistics',
                icon: <LineChartOutlined />,
                children: null,
                label: '统计',
                path: '/admin/statistics',
            },
        ] : [
            { 
                key:'Home', 
                icon: <HomeOutlined />,
                children: null,
                label: '首页',
                path: '/Home',
            },
            {
                key:'MyCart',
                icon: <ShoppingCartOutlined />,
                children: null,
                label: '购物车',
                path: '/Cart',
            },
            {
                key:'MyOrders',
                icon: <UnorderedListOutlined />,
                children: null,
                label: '全部订单',
                path: '/MyOrders',
            },
            {
                key:'MyProfile',
                icon: <IdcardOutlined />,
                children: null,
                label: '个人中心',
                path: '/MyProfile',
            },
            {
                key:'Statistics',
                icon: <LineChartOutlined />,
                children: null,
                label: '统计',
                path: '/statistics',
            },
        ]

        let {state} = useLocation();

        console.log( state)

        const info = state.info;

        const navigate = useNavigate();

        const handleMenuClick = (e) => {
            navigate(items.find(item => item.key === e.key).path);
        }

        const deleteBook = (id) => {
            if(!localStorage.getItem('mode')){
                alert('请先登录');
                return;
            }
            console.log(localStorage.getItem('mode'));
            if(localStorage.getItem('mode') === 'user') {
                alert('您没有权限删除书籍');
                return;
            }
            console.log(id);
            fetch('http://localhost:8080/deleteBook', {
                method: 'POST',
                body: id,
                credentials: 'include',
            })
            navigate('/Home');
        }

        const editBook = (id) => {
            if (!localStorage.getItem('mode')) {
              alert('请先登录');
              return;
            }
            if (localStorage.getItem('mode') === 'user') {
              alert('您没有权限编辑书籍');
              return;
            }
            fetch(`http://localhost:8080/getBook/${id}`,{credentials: 'include',})
              .then(response => response.json())
              .then(data => {
                Modal.confirm({
                  title: '编辑书籍',
                  style: { width: '80%' },
                  content: (
                    // <div>
                    //     <div>书名：<input id='bookName' type='text' defaultValue={data.title} /> </div>
                    //     <div>作者：<input id='author' type='text' defaultValue={data.author} /> </div>
                    //     <div>ISBN：<input id='isbn' type='text' defaultValue={data.isbn} /> </div>
                    //     <div>分类：<input id='type' type='text' defaultValue={data.type} /> </div>
                    //     <div>价格：<input id='price' type='text' defaultValue={data.price} /> </div>
                    //     <div>库存：<input id='inventory' type='text' defaultValue={data.inventory} /> </div>
                    //     <div>图片：<input id='image' type='text' defaultValue={data.image} /> </div>
                    //     <div>简介：<input id='description' type='text' defaultValue={data.description} /> </div>
                    // </div>
                    <div style={{ display: 'flex', flexDirection: 'column' }}>
                    <div style={{ marginBottom: '16px' }}>
                      <span style={{ fontWeight: 'bold', marginRight: '16px'}}>书名：</span>
                      <input id='bookName' type='text' defaultValue={data.title} />
                    </div>
                    <div style={{ marginBottom: '16px' }}>
                      <span style={{ fontWeight: 'bold', marginRight: '16px'}}>作者：</span>
                      <input id='author' type='text' defaultValue={data.author} />
                    </div>
                    <div style={{ marginBottom: '16px' }}>
                      <span style={{ fontWeight: 'bold', marginRight: '16px'}}>ISBN：</span>
                      <input id='isbn' type='text' defaultValue={data.isbn} />
                    </div>
                    <div style={{ marginBottom: '16px' }}>
                      <span style={{ fontWeight: 'bold', marginRight: '16px'}}>分类：</span>
                      <input id='type' type='text' defaultValue={data.type} />
                    </div>
                    <div style={{ marginBottom: '16px' }}>
                      <span style={{ fontWeight: 'bold', marginRight: '16px'}}>价格：</span>
                      <input id='price' type='text' defaultValue={data.price} />
                    </div>
                    <div style={{ marginBottom: '16px' }}>
                      <span style={{ fontWeight: 'bold', marginRight: '16px'}}>库存：</span>
                      <input id='inventory' type='text' defaultValue={data.inventory} />
                    </div>
                    <div style={{ marginBottom: '16px' }}>
                      <span style={{ fontWeight: 'bold', marginRight: '16px'}}>图片：</span>
                      <input id='image' type='text' defaultValue={data.image} />
                    </div>
                    <div style={{ marginBottom: '16px' }}>
                      <span style={{ fontWeight: 'bold', marginRight: '16px'}}>简介：</span>
                      <input id='description' type='text' defaultValue={data.description} />
                    </div>
                  </div>
                  ),
                  onOk() {
                    let bookName = document.getElementById('bookName').value;
                    let author = document.getElementById('author').value;
                    let isbn = document.getElementById('isbn').value;
                    let type = document.getElementById('type').value;
                    let price = document.getElementById('price').value;
                    let inventory = document.getElementById('inventory').value;
                    let image = document.getElementById('image').value;
                    let description = document.getElementById('description').value;
                    let book ={
                        title: bookName,
                        author: author,
                        isbn: isbn,
                        type: type,
                        price: price,
                        inventory: inventory,
                        image: image,
                        description: description,
                    }
                    let bookJson = JSON.stringify(book);
                    fetch(`http://localhost:8080/updateBook/${id}`, {
                      method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        credentials: 'include',
                      body: bookJson,
                    }).then(response => {
                      if (!response.ok) {
                        throw new Error('修改书籍失败');
                      }
                    });
                    // window.location.reload();
                  },
                });
              });
          };


        if(localStorage.getItem('mode') === 'admin') {
            return (
                <Layout style={{ minHeight: '100vh' }}>
                <Sider>
                    <div
                    style={{
                        height: 32,
                        margin: 16,
                        background: 'rgba(255, 255, 255, 0.2)',
                    }}
                    />
                    <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline" items={items} onClick={handleMenuClick} />
                </Sider>
                <Layout>
                    <Header style={{ padding: 0 }} theme="dark">
                        <HeaderInfo />
                    </Header>
                    <Content style={{ padding: 10 }} className='home-content'>
                        <div className={"content"}>
                        <div className={"book-detail"}>
                            <div className={"book-image"}><img alt="image" src={info.image} style={{width:"280px", height:"350px"}}/></div>
                            <div className={"descriptions"}>
                                <Descriptions>
                                    <Descriptions.Item className={"title "} span={3}>{info.title}</Descriptions.Item>
                                    <Descriptions.Item label="作者" span={3}>{info.author}</Descriptions.Item>
                                    <Descriptions.Item label="ISBN" span={3}>{info.isbn}</Descriptions.Item>
                                    <Descriptions.Item label="分类" span={3}>{info.type}</Descriptions.Item>
                                    <Descriptions.Item label={"定价  "} span={3}>{<span className={"price"}>￥ {info.price}</span>}</Descriptions.Item>
                                    <Descriptions.Item label="库存" span={3}>{info.inventory}</Descriptions.Item>
                                    <Descriptions.Item label="简介" span={3}>{info.description}</Descriptions.Item>
                                </Descriptions>
                                <Button className="btn" 
                                        type="danger" 
                                        color='red' 
                                        size={"large"} 
                                        ghost
                                        onClick={() => deleteBook(info.bid)}
                                        >
                                    删除书籍
                                </Button>
                                <Button className="btn"
                                        type="danger"
                                        size={"large"}
                                        style={{marginLeft: "200px"}}
                                        ghost
                                        onClick={() => editBook(info.bid)}
                                        >
                                    编辑书籍
                                </Button>
                            </div>
                        </div>
                        </div>
                    </Content>
                </Layout>
                </Layout>
            )
        }



        return (
            <Layout style={{ minHeight: '100vh' }}>
            <Sider>
                <div
                style={{
                    height: 32,
                    margin: 16,
                    background: 'rgba(255, 255, 255, 0.2)',
                }}
                />
                <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline" items={items} onClick={handleMenuClick} />
            </Sider>
            <Layout>
                <Header style={{ padding: 0 }} theme="dark">
                    <HeaderInfo />
                </Header>
                <Content style={{ padding: 10 }} className='home-content'>
                    <div className={"content"}>
                    <div className={"book-detail"}>
                        <div className={"book-image"}><img alt="image" src={info.image} style={{width:"280px", height:"350px"}}/></div>
                        <div className={"descriptions"}>
                            <Descriptions>
                                <Descriptions.Item className={"title "} span={3}>{info.title}</Descriptions.Item>
                                <Descriptions.Item label={"作者  "} span={3}>{info.author}</Descriptions.Item>
                                <Descriptions.Item label={"ISBN  "} span={3}>{info.isbn}</Descriptions.Item>
                                <Descriptions.Item label={"分类  "} span={3}>{info.type}</Descriptions.Item>
                                <Descriptions.Item label={"定价  "} span={3}>{<span className={"price"}>￥ {info.price}</span>}</Descriptions.Item>
                                <Descriptions.Item label={"状态  "} span={3}>{info.inventory !== 0? <span>有货  <span className={"inventory"}>库存{info.inventory}件</span></span> : <span className={"status"}>无货</span>}</Descriptions.Item>
                                <Descriptions.Item label={"简介  "} span={3}>{info.description}</Descriptions.Item>
                            </Descriptions>
                        </div>
                    </div>
                    <div className={"button-groups"}>
                        <Button className="btn" type="danger" color='red'  size={"large"} 
                             onClick={() => {
                                const index = booksOnCart.indexOf(info);
                                if (index === -1) {
                                    booksOnCart.push(info);
                                }
                            }}
                        >
                            加入购物车
                        </Button>
                    </div>
                    </div>
                </Content>
            </Layout>
        </Layout>
        )

}
