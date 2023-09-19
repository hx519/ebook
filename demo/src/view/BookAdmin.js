import React from "react";
import {IdcardOutlined, ShoppingCartOutlined, HomeOutlined, UnorderedListOutlined,LineChartOutlined } from '@ant-design/icons';
import { useNavigate } from "react-router";
import { Layout, Menu, Input, Button } from 'antd';
import {HeaderInfo} from "../component/HeaderInfo";
import { BookList } from "../component/BookList";


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

const BookAdmin = () => {
    const navigate = useNavigate();

    const handleMenuClick = (e) => {
        navigate(items.find(item => item.key === e.key).path);
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
                    <div className="home-content">
                        {/* <Search
                        placeholder="input search text"
                        allowClear
                        enterButton="Search"
                        size="large"
                        style={{ width: 500, height: 60, margin: "20px auto" }}
                        /> */}
                        <div >
                            <BookList id='BookList'/>
                        </div>
                    </div>
                    </Content>
                </Layout>
            </Layout>
        )
}

export default BookAdmin;
