// 引入 InputNumber 组件
import { useState, useEffect } from 'react';
import { Space, Table, Tag ,Image, Button, List, DatePicker} from 'antd';
import {booksOnCart} from "../localStorage";
import { Layout, theme, Menu } from 'antd';
import {IdcardOutlined, ShoppingCartOutlined, HomeOutlined, UnorderedListOutlined,LineChartOutlined, } from '@ant-design/icons';
import {HeaderInfo} from "../component/HeaderInfo";
import { useNavigate } from 'react-router-dom';
import { InputNumber } from 'antd';
import {Modal} from "antd";

const { Column } = Table;

const { Header, Content, Sider } = Layout;

const items =  [
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

const StaticView = () => {

    const navigate = useNavigate();

    const handleMenuClick = (e) => {
        navigate(items.find(item => item.key === e.key).path);
    }

    const [orders, setOrders] = useState([]);
    const [consumeList, setConsumeList] = useState([]);
    const [total_price, setTotal_price] = useState(0);
    const [bookQuantities, setBookQuantities] = useState([]);
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');

    useEffect(() => {
        fetch('http://localhost:8080/getOrders?Uid='+localStorage.getItem('uid'), {
            credentials: 'include',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
        }
        ).then((response) => response.json())
        .then((data) => {
            setOrders(data);
        });
    }, []);

    const {RangePicker} = DatePicker;
    const onChange = (date, dateString) => {
        console.log(date, dateString);
    };

    const onOk = (value) => {
        let hotList = [];
        let total_price = 0;
        let bookQuantities = 0;
        for(let i = 0; i < orders.length; i++){
            let orderDate = new Date(orders[i].year, orders[i].month - 1, orders[i].day, orders[i].hour, orders[i].minute);

            if(orderDate >= value[0].$d && orderDate <= value[1].$d) {
                for(let j = 0; j < orders[i].items.length; j++) {
                    if(hotList.hasOwnProperty(orders[i].items[j].title)) {
                        hotList[orders[i].items[j].title] += parseInt(orders[i].items[j].quantity);
                    }
                    else {
                        hotList[orders[i].items[j].title] = parseInt(orders[i].items[j].quantity);
                    }
                    total_price += parseInt(orders[i].items[j].price) * parseInt(orders[i].items[j].quantity);
                    bookQuantities += parseInt(orders[i].items[j].quantity);              
                }
            }
        }
        setConsumeList(hotList);
        setTotal_price(total_price);
        setBookQuantities(bookQuantities);
        console.log(consumeList);
    }

    return(
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
                <div className="Shopping">
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
                        <br />
                        <br />
                        <Table 
                            columns={[
                                {
                                    title: '书名',
                                    dataIndex: '0',
                                    key: 'title',
                                },
                                {
                                    title: '数量',
                                    dataIndex: '1',
                                    key: 'quantity',
                                },
                            ]
                                }
                            dataSource={Object.entries(consumeList)} pagination={false} >
                            {/* <Column title="书名" dataIndex="0" key="title" />
                            <Column title="数量" dataIndex="1" key="quantity" /> */}
                        </Table>
                        <br />
                        <br />
                        <div className="total">
                            <div className="total-price">
                                <span>总价：￥</span>
                                <span>{total_price}</span>
                            </div>
                            <div className="total-quantity">
                                <span>总数量：</span>
                                <span>{bookQuantities}</span>
                            </div>
                            </div>
                </div>
                </Content>
            </Layout>
        </Layout>
    )
}

export default StaticView
