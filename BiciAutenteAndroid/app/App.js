// App.js
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createDrawerNavigator } from '@react-navigation/drawer';
import { Provider as PaperProvider } from 'react-native-paper';
import MainScreen from './MainScreen';
import MyComponent from './MyComponent';

const Drawer = createDrawerNavigator();

const App = () => {
    return (
        <PaperProvider>
            <NavigationContainer>
                <Drawer.Navigator initialRouteName="Main">
                    <Drawer.Screen name="Main" component={MainScreen} />
                    <Drawer.Screen name="MyComponent" component={MyComponent} />
                </Drawer.Navigator>
            </NavigationContainer>
        </PaperProvider>
    );
};

export default App;