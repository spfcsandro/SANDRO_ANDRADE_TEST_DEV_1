import { Stock } from './stock.model';

export interface Company {
    id: number;
    stocks: Stock[];
    name: string;
    segment: string;
    standardDeviation: number;
}