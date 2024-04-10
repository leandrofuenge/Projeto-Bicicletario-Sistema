// MainScreen.js
import React from 'react';
import { View, Text, Button } from 'react-native';

const MainScreen = ({ navigation }) => {
    return (
        <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
            <Text>Tela Principal</Text>
            {/* Botão para logout */}
            <Button title="Logout" onPress={() => navigation.navigate('Login')} />
        </View>
    );
};

export default MainScreen;
