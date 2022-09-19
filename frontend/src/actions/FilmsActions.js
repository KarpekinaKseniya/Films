import axios from 'axios';

export const getFilmsData = (filters) => {
    return axios.get('/v1/films' + buildParams(filters))
        .then(response => response.data)
        .catch(error => alert(error))
}

const buildParams = (filters) => {
    const {lastName, releasedFrom, releasedUntil, page, size} = filters
    const lastNameParam = '?lastName=' + lastName
    const releasedFromParam = '&releasedFrom=' + releasedFrom
    const releasedUntilParam = '&releasedUntil=' + releasedUntil
    const pageParam = '&page=' + page
    const sizeParam = '&size=' + size

    return lastNameParam.concat(releasedFromParam, releasedUntilParam, pageParam, sizeParam)
}
