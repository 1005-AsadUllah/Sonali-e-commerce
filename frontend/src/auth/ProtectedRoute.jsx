import { useContext } from "react";
import { Navigate } from "react-router-dom";
import { AuthContext } from "./AuthContext";

const ProtectedRoute = ({ children, role }) => {
    const { user } = useContext(AuthContext);

    if (!user) return <Navigate to="/login" />;

    if (role && user.role !== role) {
        return <h2>403 – Forbidden</h2>;
    }

    return children;
};

export default ProtectedRoute;
