// MainScreen.js
import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';

const MainScreen = ({ navigation }) => {
    return (
        <View style={styles.container}>
            <Text>Tela Principal</Text>
            <Button title="Abrir Menu" onPress={() => navigation.openDrawer()} />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
    },
});

export default MainScreen;