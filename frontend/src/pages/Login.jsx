import { useContext, useState } from "react";
import api from "../api/axios";
import { AuthContext } from "../auth/AuthContext";

const Login = () => {
    const { login } = useContext(AuthContext);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        const res = await api.post("/auth/login", { email, password });
        login(res.data.accessToken);
    };

    return (
        <form onSubmit={handleSubmit}>
            <input placeholder="Email"
                   onChange={e => setEmail(e.target.value)} />
            <input type="password"
                   placeholder="Password"
                   onChange={e => setPassword(e.target.value)} />
            <button type="submit">Login</button>
        </form>
    );
};

export default Login;
