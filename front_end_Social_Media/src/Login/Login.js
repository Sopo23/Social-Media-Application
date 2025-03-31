  import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../App.css';
import AuthService from '../Service/AuthService';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const authService = new AuthService();
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await authService.login(username, password);
            setMessage('Login successful');
            console.log(response.data);
            navigate('/profile'); // Redirect to Profile page
        } catch (error) {
            setMessage('Login failed');
            console.error(error);
        }
    };

    return (
        <div className="auth-container">
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <input
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <button type="submit" className="auth-button">Login</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
};

export default Login;
