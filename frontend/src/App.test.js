import {render} from '@testing-library/react';
import {createMemoryHistory} from 'history';
import App from './App';

test('renders learn react link', () => {
    const history = createMemoryHistory({initialEntries: ['/films']});
    render(<App/>);
    expect(history.location.pathname).toBe('/films');
});