import React from "react";
import { IdcardOutlined, ShoppingCartOutlined, HomeOutlined, UnorderedListOutlined,LineChartOutlined } from '@ant-design/icons';
import { useNavigate } from "react-router";
import { Layout, Menu, Input, Button, List, Card, Table } from 'antd';
import { HeaderInfo } from "../component/HeaderInfo";
import { useEffect } from "react";
import { useState } from "react";

const { Column } = Table;

const { Search } = Input;

const { Header, Content, Sider } = Layout;

const items = [
  {
    key: 'BookAdmin',
    icon: <UnorderedListOutlined />,
    children: null,
    label: '图书管理',
    path: '/admin/books',
  },
  {
    key: 'OrderAdmin',
    icon: <ShoppingCartOutlined />,
    children: null,
    label: '订单管理',
    path: '/admin/orders',
  },
  {
    key: 'UserAdmin',
    icon: <IdcardOutlined />,
    children: null,
    label: '用户管理',
    path: '/admin/users',
  },
  {
    key: 'Statistics',
    icon: <LineChartOutlined />,
    children: null,
    label: '统计',
    path: '/admin/statistics',
  },
]

const OrderAdmin = () => {
  const navigate = useNavigate();

  const handleMenuClick = (e) => {
    navigate(items.find((item) => item.key === e.key).path);
  };

  const [data, setData] = useState([]);
  const [searchValue, setSearchValue] = useState("");

  useEffect(() => {
    if (!localStorage.getItem('mode')) {
      alert('请先登录');
      return;
    }
    let url = 'http://localhost:8080/getAllOrders';
    if (searchValue) {
      url = 'http://localhost:8080/search?keyword=' + searchValue;
    }
    fetch(url, {
      credentials: 'include',
      method: 'POST',
      body: localStorage.getItem('uid'),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setData(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [searchValue]);

  const handleSearch = (value) => {
    setSearchValue(value);
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
        <Content style={{ padding: 10 }} className="home-content">
          <div style={{ marginBottom: 16 }}>
            <Search placeholder="请输入书名或时间范围(xxxx/xx/xx-xxxx/xx/xx)" onSearch={handleSearch} enterButton />
          </div>
          <List
            id="order-list"
            grid={{ gutter: 16, column: 1 }}
            dataSource={data}
            renderItem={(order) => (
              <List.Item>
                <Card title={`用户名：${order.username}`} style={{ width: '100%' }}>
                  <a>订单日期：{order.year}-{order.month}-{order.day}  {order.hour}时{order.minute}分</a>
                  <Table dataSource={order.items} pagination={false}>
                    <Column title="书名" dataIndex="title" />
                    <Column title="作者" dataIndex="author" />
                    <Column title="数量" dataIndex="quantity" />
                    <Column title="单价" dataIndex="price" />
                  </Table>
                  <p style={{ textAlign: 'right' }}>总价：{order.price}</p>
                </Card>
              </List.Item>
            )}
          />
        </Content>
      </Layout>
    </Layout>
  );
}

export default OrderAdmin;
