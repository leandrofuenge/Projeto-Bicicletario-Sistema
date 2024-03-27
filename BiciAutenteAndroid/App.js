import React, { useState } from 'react';
import { View, TextInput, Button, Alert, StyleSheet } from 'react-native';
import axios from 'axios';

const LoginScreen = () => {
    const [cpf, setCpf] = useState('');
    const [senha, setSenha] = useState('');

    const fazerLogin = async () => {
        try {
            const response = await axios.post('http://localhost:8080/login', { cpf, senha });

            if (response.status === 200) {
                const usuario = response.data;
                console.log('Login bem-sucedido:', usuario);
                // Faça algo com os dados do usuário, como navegar para a próxima tela
            } else {
                const errorResponse = response.data;
                Alert.alert('Erro', errorResponse.error);
            }
        } catch (error) {
            console.error('Erro ao fazer login:', error);
            Alert.alert('Erro', 'Erro ao fazer login. Por favor, tente novamente.');
        }
    };

    return (
        <View style={styles.container}>
            <TextInput
                style={styles.input}
                placeholder="CPF"
                onChangeText={setCpf}
                value={cpf}
            />
            <TextInput
                style={styles.input}
                placeholder="Senha"
                onChangeText={setSenha}
                value={senha}
                secureTextEntry
            />
            <Button title="Login" onPress={fazerLogin} />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#fff',
        padding: 20,
    },
    input: {
        width: '100%',
        height: 40,
        borderColor: 'gray',
        borderWidth: 1,
        borderRadius: 5,
        marginBottom: 10,
        paddingHorizontal: 10,
    },
});

export default LoginScreen;
