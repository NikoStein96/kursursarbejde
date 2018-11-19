
import React from 'react';
import WhosMychar from './components/WhosMychar';
import Input from './components/Input';
import { StyleSheet, Text, View, ActivityIndicator} from 'react-native';

export default class App extends React.Component {

  constructor(){
    super()
    this.state ={
      haveFetched: false,
      chn: [],
    }
  }

  onSubmit = (n1,n2,n3,n4,n5)=>{
    this.setState({
      haveFetched: true,
      chn: [n1,n2,n3,n4,n5],
    });
  }
  
 
  render(){
  if (this.state.haveFetched ){ 
      return (     
        
          <View style={styles.container}>
          <WhosMychar choises={this.state.chn}/>
          </View>
          );
        }
        else {
          return(
          <View style={styles.container}>
          <Text> enter 5 digits to get some returns from the api </Text>
          <Input  submitting={this.onSubmit}/>
          </View>
          );

        }
          
        
      
    }
  }
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  item: {
    flex: 1,
    alignSelf: 'stretch',
    margin: 10,
    alignItems: 'center',
    justifyContent: 'center',
    borderBottomWidth: 1,
    borderBottomColor: '#eee'
  }
});