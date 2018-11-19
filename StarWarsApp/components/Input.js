import React from 'react';
import { StyleSheet, Text, View,TextInput,Button } from 'react-native';


export default class Input extends React.Component {
    constructor(){
        super()
        this.state ={
            text1: 0,
            text2: 0,
            text3: 0,
            text4: 0,
            text5: 0,

        }

    }

    submitting = () => {
        this.props.submitting(this.state.text1,this.state.text2,this.state.text3,this.state.text4,this.state.text5)
    }

    render() {

        return (
            <View>
                <TextInput
    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
    onChangeText={(text1) => this.setState({text1})}
    value={this.state.text1}
  />
  <TextInput
    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
    onChangeText={(text2) => this.setState({text2})}
    value={this.state.text2}
  />
  <TextInput
    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
    onChangeText={(text3) => this.setState({text3})}
    value={this.state.text3}
  />
  <TextInput
    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
    onChangeText={(text4) => this.setState({text4})}
    value={this.state.text4}
  />
  <TextInput
    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
    onChangeText={(text5) => this.setState({text5})}
    value={this.state.text5}
  />
  <Button
  title="get api"
  color="#841584"
  onPress={this.submitting}
/>
            </View>
        )



    }




}