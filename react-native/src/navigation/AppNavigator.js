import React, { useEffect } from "react";
import { Linking } from "react-native";
import { connect } from "react-redux";

import LoginScreen from "../screens/login/LoginScreen";
import HomeScreen from "../screens/home/HomeScreen";
import StartupScreen from "../screens/startup/StartupScreen";
import actions from "../store/actions";
import GoMetroTracking from "../modules/tracking/GoMetroTracking";

import { PermissionsAndroid } from "react-native";

const requestCameraPermission = async () => {
    try {
        const granted = await PermissionsAndroid.request(
            PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
            {
                title: "Cool Photo App Camera Permission",
                message:
                    "Cool Photo App needs access to your camera " +
                    "so you can take awesome pictures.",
                buttonNeutral: "Ask Me Later",
                buttonNegative: "Cancel",
                buttonPositive: "OK"
            }
        );
        if (granted === PermissionsAndroid.RESULTS.ACCESS_FINE_LOCATION) {
            console.log("You can use the camera");
        } else {
            console.log("Camera permission denied");
        }
    } catch (err) {
        console.warn(err);
    }
};


const AppNavigator = (props) => {

    const {
        authenticated,
        completeAuthentication,
        initialisationFailed,
        initialised,
        initialise,
        initialising,
        user
    } = props;

    useEffect(() => {
        Linking.addEventListener("url", handleOpenURL);
        return (() => {
            Linking.removeEventListener("url", handleOpenURL);
        })
    }, []);

    const handleOpenURL = (event) => {
        if (event && event.url && event.url.indexOf("code=") >= 0) {
            completeAuthentication(event.url);
        }
    };

    if (initialisationFailed) {
        // TODO Show a better error page
        return <h1>Something went wrong!!</h1>;
    }

    if (initialising) {
        // TODO Show a better loading page
        return <StartupScreen/>;
    }

    if (!initialised) {
        initialise();
        return <StartupScreen/>;
    }

    if (!authenticated) {
        return <LoginScreen/>
    }

    GoMetroTracking.initialise(

    )

    requestCameraPermission();

    return <HomeScreen/>;
};

const mapStateToProps = (state) => ({
    authenticated: state.authentication.authenticated,
    initialisationFailed: state.authentication.initialisationFailed,
    initialised: state.authentication.initialised,
    initialising: state.authentication.initialising,
    user: state.authentication.user,
});

const mapDispathToProps = (dispatch) => ({
    completeAuthentication: (url) => dispatch(actions.authentication.completeAuthentication(url)),
    initialise: () => dispatch(actions.authentication.initialise()),
});

export default connect(mapStateToProps, mapDispathToProps)(AppNavigator);
