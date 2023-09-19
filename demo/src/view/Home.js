import React, { useEffect } from "react";
import  BookCarousel  from "../component/BookCarousel";
import { BookList } from "../component/BookList";
import { Input, Space } from 'antd';
import {IdcardOutlined, ShoppingCartOutlined, HomeOutlined, UnorderedListOutlined,LineChartOutlined } from '@ant-design/icons';
import { Layout, theme, Menu } from 'antd';
import {HeaderInfo} from "../component/HeaderInfo";
import { useNavigate } from 'react-router-dom';

const { Search } = Input;

const { Header, Content, Sider } = Layout;

const items = [
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

const Home = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const mode = localStorage.getItem('mode');
        if (mode === 'admin') {
            navigate('/admin/books')
        }
    }, []);

    const handleMenuClick = (e) => {
        navigate(items.find(item => item.key === e.key).path);
    }

    return (
        <Layout style={{ minHeight: '100vh' }}>
            <Sider>
                <div style={{ height: 32, margin: 16, background: 'rgba(255, 255, 255, 0.2)' }} />
                <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline" items={items} onClick={handleMenuClick} />
            </Sider>
            <Layout>
                <Header style={{ padding: 0 }} theme="dark">
                    <HeaderInfo />
                </Header>
                <Content style={{ padding: 10 }} className='home-content'>
                    <div className="home-content">
                        {/* <Search
                            placeholder="input search text"
                            allowClear
                            enterButton="Search"
                            size="large"
                            style={{ width: 500, height: 60, margin: "20px auto" }}
                        /> */}
                        <div style={{height: "350px"}}>
                            <BookCarousel id='Carousel'/>
                        </div>
                        <div >
                            <BookList id='BookList'/>
                        </div>
                    </div>
                </Content>
            </Layout>
        </Layout>
    )
}

export default Home;
