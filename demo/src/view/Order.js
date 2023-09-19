import { useState, useEffect } from 'react';
import { Table, Card, Input } from 'antd';
import { booksOnCart } from "../localStorage";
import { Layout, theme, Menu, List } from 'antd';
import { IdcardOutlined, ShoppingCartOutlined, HomeOutlined, UnorderedListOutlined,LineChartOutlined } from '@ant-design/icons';
import { HeaderInfo } from "../component/HeaderInfo";
import { useNavigate } from 'react-router-dom';
// import Search from 'antd/es/transfer/search';

const { Column } = Table;
const { Search } = Input;

const { Header, Content, Sider } = Layout;

const items = [
  {
    key: 'Home',
    icon: <HomeOutlined />,
    children: null,
    label: '首页',
    path: '/Home',
  },
  {
    key: 'MyCart',
    icon: <ShoppingCartOutlined />,
    children: null,
    label: '购物车',
    path: '/Cart',
  },
  {
    key: 'MyOrders',
    icon: <UnorderedListOutlined />,
    children: null,
    label: '全部订单',
    path: '/MyOrders',
  },
  {
    key: 'MyProfile',
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
];

const Order = () => {
  const navigate = useNavigate();

  const handleMenuClick = (e) => {
    navigate(items.find((item) => item.key === e.key).path);
  };

  // const [data, setData] = useState([]);

  // useEffect(() => {
  //   if (!localStorage.getItem('mode')) {
  //     alert('请先登录');
  //     return;
  //   }
  //   fetch('http://localhost:8080/getOrders', {
  //     method: 'POST',
  //     body: localStorage.getItem('uid'),
  //   })
  //     .then((response) => response.json())
  //     .then((data) => {
  //       console.log(data);
  //       setData(data);
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // }, []);

  // return (
  //   <Layout style={{ minHeight: '100vh' }}>
  //     <Sider>
  //       <div
  //         style={{
  //           height: 32,
  //           margin: 16,
  //           background: 'rgba(255, 255, 255, 0.2)',
  //         }}
  //       />
  //       <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline" items={items} onClick={handleMenuClick} />
  //     </Sider>
  //     <Layout>
  //       <Header style={{ padding: 0 }} theme="dark">
  //         <HeaderInfo />
  //       </Header>
  //       <Content style={{ padding: 10 }} className="home-content">
  //         <List
  //           id="order-list"
  //           grid={{ gutter: 16, column: 1 }}
  //           dataSource={data}
  //           renderItem={(order) => (
  //             <List.Item>
  //               <Card title={`用户名：${order.username}`} style={{ width: '100%' }}>
  //                 <a>订单日期：{order.year}-{order.month}-{order.day}  {order.hour}时{order.minute}分</a>
  //                 <Table dataSource={order.items} pagination={false}>
  //                   <Column title="书名" dataIndex="title" />
  //                   <Column title="作者" dataIndex="author" />
  //                   <Column title="数量" dataIndex="quantity" />
  //                   <Column title="单价" dataIndex="price"/>
  //                 </Table>
  //                 <p style={{ textAlign: 'right' }}>总价：{order.price}</p>
  //               </Card>
  //             </List.Item>
  //           )}
  //         />
  //       </Content>
  //     </Layout>
  //   </Layout>
  // );


  
  const [data, setData] = useState([]);
  const [searchValue, setSearchValue] = useState("");

  useEffect(() => {
    if (!localStorage.getItem('mode')) {
      alert('请先登录');
      return;
    }
    let url = 'http://localhost:8080/getOrders?Uid=' + localStorage.getItem('uid');
    if (searchValue) {
      url = 'http://localhost:8080/searchMy?keyword=' + searchValue + '&Uid=' + localStorage.getItem('uid');
    }
    fetch(url, {
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
};

export default Order;
