import React from 'react';
import { StyleSheet, Text, View, ActivityIndicator } from 'react-native';


export default class WhosMychar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      dataSource: null,
      //choise: this.props.choises,
    }
  }

  componentDidMount() {

    return fetch('https://stein.guru/starwars/api/starwars/people/arrayOfPeople/?list=' + {this.props.choises[0]} + '&list=' + this.props.choises[1] + '&list=' + this.props.choises[2] + '&list=' + this.props.choises[3] + '&list=' + this.props.choises[4])
    console.log(this.state.choise)
      .then((response) => response.json())
      .then((responseJson) => {
        this.setState({
          isLoading: false,
          dataSource: responseJson.results,
        })
      })
      .catch((error) => {
        console.log(error)
      });
  }
  render() {
    if (this.state.isLoading) {
      return (
        <View style={styles.container}>
          <ActivityIndicator />
        </View>
      )
    } else {
      let chars = this.state.dataSource.map((val, key) => {
        return (
        <View key={key} style={styles.item}>
        <Text>{val.firstperson.name}</Text>
        <Text>{val.secondperson.name}</Text>
        <Text>{val.thirdperson.name}</Text>
        <Text>{val.forthperson.name}</Text>
        <Text>{val.fifthperson.name}</Text>
        </View>
      )});
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