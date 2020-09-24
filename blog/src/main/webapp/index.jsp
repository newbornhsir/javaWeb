<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<%@include file="templates/head.jsp" %>
<body>
	<div id="app" style="height: 100%;"></div>
<script type="text/babel">


class App extends React.Component {
	constructor(props) {
		super(props);
		this.formRef = React.createRef();
		this.state = {visible: false};
	}
	submit({key}) {
		// this.setState({visible: false});
		// console.log(this.formRef.current.getFieldsValue());
		const path = key === '1' ? 'login.jsp' : 'register.jsp';
		window.location.href = path;
	}
	openModal({key}) {
		// this.setState({visible: true});
		const path = key === '1' ? 'login.jsp' : 'register.jsp';
		window.location.href = path;
	}
	render() {
		return (
			<antd.Layout style={{height: '100%'}}>
				<antd.Layout.Header>
					<div style={{
						width: '120px',
						height: '31px',
						backgroundColor: 'rgba(255,255,255,0.2)',
						margin: '16px 28px 16px 0',
						float: 'left',
					}}></div>
					<antd.Menu theme="dark" mode="horizontal" style={{display: 'inline-block'}}>
						<antd.Menu.Item key="1">menu1</antd.Menu.Item>	
						<antd.Menu.Item key="2">menu2</antd.Menu.Item>	
						<antd.Menu.Item key="3">menu3</antd.Menu.Item>	
					</antd.Menu>
					<antd.Menu 
						theme="dark" 
						mode="horizontal"
						onClick={this.openModal.bind(this)}
						style={{
							float: 'right',
							dispaly: 'inline-block'
						}}>
						<antd.Menu.Item key="1">登陆</antd.Menu.Item>
						<antd.Menu.Item key="2">注册</antd.Menu.Item>
					</antd.Menu>
				</antd.Layout.Header>
				<antd.Layout>
					<antd.Layout.Sider />
					<antd.Layout.Content />
				</antd.Layout>
				<antd.Layout.Footer>
					<div>footer</div>
				</antd.Layout.Footer>
				<antd.Modal
					title="表单" 
					visible={this.state.visible} 
					onOk={this.submit.bind(this)} 
					onCancel={() => this.setState({visible: false})}>
					<antd.Form ref={this.formRef}>
						<antd.Form.Item labelCol={8} wrapperCol={16} label="用户名" name="username" rules={[{required: true}]}>
							<antd.Input />	
						</antd.Form.Item>
						<antd.Form.Item labelCol={8} wrapperCol={16} label="密码" name="password" rules={[{required: true}]}>
							<antd.Input />	
						</antd.Form.Item>
						<antd.Form.Item labelCol={8} wrapperCol={16} label="确认密码" name="repassword" rules={[{required: true}]}>
							<antd.Input />	
						</antd.Form.Item>
					</antd.Form>
				</antd.Modal>
			</antd.Layout>
		)
	}
}
ReactDOM.render(
	<App />,
	document.getElementById('app')
);
</script>
</body>
</html>
