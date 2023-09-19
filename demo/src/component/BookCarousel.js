import React from "react";
import { Carousel } from "antd";


// const BookCarousel = () => {
//     const onChange = (currentSlide) => {
//       console.log(currentSlide);
//     };
//     return (
//         <div>
//         <Carousel afterChange={onChange} autoplay>
//             <div>
//                 <img alt="1" src={require("../assets/carousel/book1.jpg")} style={{ height:140 }}/>
//             </div>
//             <div>
//                 <img alt="2" src={require("../assets/carousel/book2.jpg")} style={{ height:140 }}/>
//             </div>
//             <div>
//                 <img alt="3" src={require("../assets/carousel/book3.jpg")} style={{ height:140 }}/>
//             </div>
//             <div>
//                 <img alt="4" src={require("../assets/carousel/book4.jpg")} style={{ height:140 }}/>
//             </div>
//         </Carousel>
//         </div>
//     );
//   };

// export default BookCarousel;

export default class BookCarousel extends React.Component{

    createContent = (ctx) => {
        const images = ctx.keys().map(ctx);
        console.log(images);
        let result = [];
        for (let i = 0; i < ctx.keys().length; i++) {
            let img = images[i];
            console.log(img);
            result.push(<div><img alt={i} src={img}/></div>);
        }
        return result;
    };


    render(){
        const requireContext = require.context("../assets/carousel", true, /^\.\/.*\.jpg$/);

        return (
            <Carousel autoplay>
                {this.createContent(requireContext)}
            </Carousel>
        )
    }
}



