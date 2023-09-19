import React, { useEffect } from "react";
import {IdcardOutlined, ShoppingCartOutlined, HomeOutlined, UnorderedListOutlined,LineChartOutlined } from '@ant-design/icons';
import { useNavigate } from "react-router";
import { Layout, Menu, Input, Button, DatePicker, Space, Table, Row, Col, List, Card } from 'antd';
import {HeaderInfo} from "../component/HeaderInfo";
import { BookList } from "../component/BookList";
import { useState } from "react";


const { Search } = Input;

const { Header, Content, Sider } = Layout;

const items = [
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
]

const AdminView = () => {
    const navigate = useNavigate();

    const handleMenuClick = (e) => {
        navigate(items.find(item => item.key === e.key).path);
    }

    const [orders, setOrders] = useState([]);
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [hotList, setHotList] = useState([]);
    const [consumeList, setConsumeList] = useState([]);

    useEffect(() => {
        fetch(
            'http://localhost:8080/getAllOrders', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
            }
        )
            .then(res => res.json())
            .then(res => {
                console.log(res);
                setOrders(res);
            })
    }, [])

    const { RangePicker } = DatePicker;
    const onChange = (value, dateString) => {
        console.log('Selected Time: ', value);
        console.log('Formatted Selected Time: ', dateString);
    };
    // const onOk = (value) => {
    //     // 获取时间
    //     setEndDate(value[1]._d);
    //     setStartDate(value[0]._d);
        
    //     // 筛选消费榜和热销榜
    //     let consumeList = [];
    //     let hotList = [];
    //     for(let i = 0; i < orders.length; i++) {
    //         let orderDate = new Date(orders[i].year, orders[i].month - 1, orders[i].day, orders[i].hour, orders[i].minute);
    //         console.log(orderDate);
    //         if(orderDate >= value[0]._d && orderDate <= value[1]._d) {
    //             for(let j = 0; j < orders[i].items.length; j++) {
    //                 if(orders[i].items[j].title in hotList) {
    //                     hotList[orders[i].items[j].title] += orders[i].items[j].quantity;
    //                 }
    //                 else {
    //                     hotList[orders[i].items[j].title] = orders[i].items[j].quantity;
    //                 }                  
    //             }
    //             if(orders[i].username in consumeList) {
    //                 consumeList[orders[i].username] += orders[i].price;
    //             }
    //             else {
    //                 consumeList[orders[i].username] = orders[i].price;
    //             }
    //         }
    //     }
    //     console.log(hotList);
    //     console.log(consumeList);
    // };
    const onOk = (value) => {
        // 筛选消费榜和热销榜
        let consumeList = [];
        let hotList = [];
        for(let i = 0; i < orders.length; i++) {
            let orderDate = new Date(orders[i].year, orders[i].month - 1, orders[i].day, orders[i].hour, orders[i].minute);

            if(orderDate >= value[0].$d && orderDate <= value[1].$d) {
                for(let j = 0; j < orders[i].items.length; j++) {
                    if(hotList.hasOwnProperty(orders[i].items[j].title)) {
                        hotList[orders[i].items[j].title] += parseInt(orders[i].items[j].quantity);
                    }
                    else {
                        hotList[orders[i].items[j].title] = parseInt(orders[i].items[j].quantity);
                    }                  
                }
                if(consumeList.hasOwnProperty(orders[i].username)) {
                    consumeList[orders[i].username] += parseInt(orders[i].price);
                }
                else {
                    consumeList[orders[i].username] = parseInt(orders[i].price);
                }
            }
        }
        console.log(hotList);
        console.log(consumeList);
        // 消费榜排序
        let consumeListSorted = [];
        for(let key in consumeList) {
            consumeListSorted.push([key, consumeList[key]]);
        }
        consumeListSorted.sort(function(a, b) {
            return b[1] - a[1];
        }
        )
        console.log(consumeListSorted);
        setConsumeList(consumeListSorted);
        // 热销榜排序
        let hotListSorted = [];
        for(let key in hotList) {
            hotListSorted.push([key, hotList[key]]);
        }
        hotListSorted.sort(function(a, b) {
            return b[1] - a[1];
        }
        )
        console.log(hotListSorted);
        setHotList(hotListSorted);
    };
    

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
                    <div className="home-content">
                        <div >
                        <Space direction="vertical" size={12}>
                            <RangePicker
                            showTime={{
                                format: 'HH:mm',
                            }}
                            format="YYYY-MM-DD HH:mm"
                            onChange={onChange}
                            onOk={onOk}
                            />
                        </Space>
                        <Row gutter={[16, 16]}>
                            <Col span={12}>
                                <h1>消费榜</h1>
                                <Table columns={
                                    [
                                        {
                                            title: '用户名',
                                            dataIndex: '0',
                                            key: 'username',
                                        },
                                        {
                                            title: '消费金额',
                                            dataIndex: '1',
                                            key: 'price',
                                        },
                                    ]
                                } dataSource={consumeList} 
                                pagination={false}
                                />
                            </Col>
                            <Col span={12}>
                                <h1>热销榜</h1>
                                <Table columns={
                                    [
                                        {
                                            title: '书名',
                                            dataIndex: '0',
                                            key: 'title',
                                        },
                                        {
                                            title: '销量',
                                            dataIndex: '1',
                                            key: 'quantity',
                                        },
                                    ]
                                } dataSource={hotList}
                                pagination={false}
                                />
                            </Col>
                        </Row>
                        {/* // <h1>热销榜</h1>
                        // <Table columns={
                        //     [
                        //         {
                        //             title: '书名',
                        //             dataIndex: '0',
                        //             key: 'title',
                        //         },
                        //         {
                        //             title: '销量',
                        //             dataIndex: '1',
                        //             key: 'quantity',
                        //         },
                        //     ]
                        // } dataSource={hotList} 
                        // pagination={false}
                        // />
                        // <h1>消费榜</h1>
                        // <Table columns={
                        //     [
                        //         {
                        //             title: '用户名',
                        //             dataIndex: '0',
                        //             key: 'username',
                        //         },
                        //         {
                        //             title: '消费金额',
                        //             dataIndex: '1',
                        //             key: 'price',
                        //         },
                        //     ]
                        // } dataSource={consumeList} 
                        // pagination={false}
                        // /> */}
                        </div>
                    </div>
                    </Content>
                </Layout>
            </Layout>
        )
}

export default AdminView;