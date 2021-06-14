import axios from "axios";
import { uri }  from './config/index';

export default axios.create({
  baseURL: uri.local,
  headers: {
    "Content-type": "application/json"
  }
});