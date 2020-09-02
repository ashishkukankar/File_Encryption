import * as React from 'react';
import { View, StyleSheet, Text,NativeModules } from 'react-native';

export interface AppProps {
}

export interface AppState {
    user:string
}

export default class Home extends React.Component<AppProps, AppState> {
 
  
  constructor(props: AppProps) {
    super(props);
    this.state = {
        user:''
    };
    //this.accessEncryptedFile();
  }

   accessEncryptedFile(){
    NativeModules.writeEncryptedFile();
    var text = NativeModules.readEncryptedFile();
    console.log(text);
   }

  public render() {
    return (
      <View>
         <Text>App Component</Text>
      </View>
    );
  }
}
