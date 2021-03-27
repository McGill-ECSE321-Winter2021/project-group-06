function PersonDto (name) {
  this.name = name
  this.events = []
}

function EventDto (name, date, start, end) {
  this.name = name
  this.eventDate = date
  this.startTime = start
  this.endTime = end
}
export default {
  name: 'eventregistration',
  data () {
    return {
      persons: [],
      newPerson: '',
      errorPerson: '',
      response: []
    }
  },
  created: function () {
    // Test data
    const p1 = new PersonDto('John')
    const p2 = new PersonDto('Jill')
    // Sample initial content
    this.persons = [p1, p2]
  },
}

methods: {
    createPerson: function (personName) {
      // Create a new person and add it to the list of people
      var p = new PersonDto(personName)
      this.persons.push(p)
      // Reset the name field for new people
      this.newPerson = ''
    }
  }
  


  