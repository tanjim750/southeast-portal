import { ButtonModel } from "./button.model";

export interface HomeModel{
    id?: number;
    slogan?: string;
    sloganIconUrl?: string;
    circle?: string;
    title?: string;
    details?: string;
    imageOneUrl?: string;
    imageTwoUrl?: string;
    buttons?: ButtonModel[];
}