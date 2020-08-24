import { cyan } from "color-name"
import { AssertionError } from "assert"

/*
Task #4
Write down a simple integration test to the task #3 you did before.
No need to check all the data retrieved by the button pushing. Just a Company name would be enough !
*/
describe('Test to be fulfilled by the candidate', () => {
  it('push the button implemented on task #3 and shows the company names', () => {
      cy.visit('http://localhost:4200')
      cy.get('[data-cy="btn-get-companies"]').click()
      cy.get('[data-cy="table-company-row"]').should('to.have.length',3)
      cy.contains('Nitryx').should('to.have.length',1)
      cy.contains('Volvo').should('to.have.length',1)
      cy.contains('Heineken').should('to.have.length',1)


  })
})
