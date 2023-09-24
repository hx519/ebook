import React from "react";
import {IdcardOutlined, ShoppingCartOutlined, HomeOutlined, UnorderedListOutlined,LineChartOutlined } from '@ant-design/icons';
import { useNavigate } from "react-router";
import { Layout, Menu, Input, Button, List, Card, message } from 'antd';
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

const UserAdmin = () => {
    const navigate = useNavigate();

    const handleMenuClick = (e) => {
        navigate(items.find(item => item.key === e.key).path);
    }

    const [data, setData] = React.useState([]);
    const [disabled, setDisabled] = React.useState(false);

    React.useEffect(() => {
        if (!localStorage.getItem('mode')) {
            alert('请先登录');
            return;
        }
        fetch('http://localhost:8080/getAllUsers', {
            credentials: 'include',
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': localStorage.getItem('mode'),
            },
        })
        .then((res) => res.json())
        .then((res) => {
            console.log(res);
            setData(res);
        })
        .catch((err) => {
            alert(err.message);
        })
    }, []);

    const handleDisableUser = (id) => {
        fetch(`http://localhost:8080/changeMode`, {
            credentials: 'include',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': localStorage.getItem('mode'),
            },
            body: JSON.stringify({
                uid: id,
            }),
        })
        .then((res) => res.json())
        .then((res) => {
            if(res === true)
                message.success('操作成功');
            else
                message.error('操作失败');
            window.location.reload();
        })
        .catch((err) => {
            alert(err.message);
        })
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
                            <List 
                                grid={{
                                  gutter: 16,
                                  column: 2,
                                }}
                                dataSource={data.filter(item => item.mode === 'user' || item.mode === 'ban')}
                                renderItem={item => (
                                    <List.Item>
                                        <Card title={item.username} >
                                            <p>邮箱： {item.email}</p>
                                            <p>电话： {item.phone}</p>
                                            <p>地址： {item.address}</p>
                                            <Button type="primary" danger={item.mode === "ban"} onClick={() => handleDisableUser(item.uid)}>
                                                {item.mode === "ban" ? '解禁' : '禁用'}
                                            </Button>
                                        </Card>
                                    </List.Item>
                                )}
                            />
                    </div>
                </Content>
            </Layout>
        </Layout>
    );
}

export default UserAdmin;
