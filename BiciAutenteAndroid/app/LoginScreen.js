// LoginScreen.js
import React, { useState } from 'react';
import { View, Text, TextInput, Button, Alert, StyleSheet } from 'react-native';
import axios from 'axios';

const LoginScreen = ({ navigation }) => {
    const [cpf, setCpf] = useState('');
    const [senha, setSenha] = useState('');

    const handleLogin = async () => {
        try {
            const response = await axios.post('http://192.168.1.17:1010/login', { cpf, senha });
            // Se o login for bem-sucedido, você pode redirecionar para a tela principal
            navigation.navigate('Main');
        } catch (error) {
            Alert.alert('Erro', 'CPF ou senha inválidos.');
        }
    };

    return (
        <View style={styles.container}>
            <Text style={styles.label}>CPF:</Text>
            <TextInput
                value={cpf}
                onChangeText={setCpf}
                placeholder="Digite seu CPF"
                style={styles.input}
            />
            <Text style={styles.label}>Senha:</Text>
            <TextInput
                value={senha}
                onChangeText={setSenha}
                placeholder="Digite sua senha"
                secureTextEntry
                style={styles.input}
            />
            <Button title="Login" onPress={handleLogin} />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        paddingHorizontal: 20,
    },
    label: {
        fontSize: 16,
        marginBottom: 5,
    },
    input: {
        width: '100%',
        height: 40,
        borderWidth: 1,
        borderColor: '#ccc',
        borderRadius: 5,
        paddingHorizontal: 10,
        marginBottom: 10,
    },
});

export default LoginScreen;