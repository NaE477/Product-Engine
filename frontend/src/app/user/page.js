"use client"; // Add this at the top to declare it as a Client Component

import { useEffect, useState } from 'react';

const UserPage = () => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        // Fetch user data from the backend
        const fetchUser = async () => {
            try {
                const response = await fetch('/api/user'); // Use the relative path
                const data = await response.json();
                setUser(data);
            } catch (error) {
                console.error("Error fetching user data:", error);
            }
        };
        fetchUser();
    }, []);

    return (
        <div>
            {user ? (
                <div>
                    <h1>User Information</h1>
                    <p>Name: {user.username}</p>
                    <p>Email: {user.email}</p>
                </div>
            ) : (
                <p>Loading user data...</p>
            )}
        </div>
    );
};

export default UserPage;
