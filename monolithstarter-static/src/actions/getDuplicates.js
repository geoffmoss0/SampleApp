import axios from 'axios';

export async function getDuplicates() {
  return (await axios.get('/api/duplicates')).data;
}
