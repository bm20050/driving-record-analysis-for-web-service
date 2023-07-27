import LoadingButton from "./LoadingButton";
import SelectPlate from "./SelectPlate";
import SelectDanger from "./SelectDanger";


const SelectBox = (props) => {

    return (
        <>
            <form className="row gy-2 gx-3 align-items-center">
                <div className="col-auto" id="calendar">
                    <input className="form-select-sm" type="date" defaultValue={"2022-12-01"} ref={props.reqDate} onChange={props.handleDate} name="reqdate" style={{ border: "1px lightgray solid" }} />
                </div>
                <div className="col-auto">
                    <SelectPlate plate={props.plate} />
                </div>
                <div className="col-auto">
                    <SelectDanger danger={props.danger} />
                </div>
                <div className="col-auto" id="button">
                    <LoadingButton searchData={props.searchData} next={props.next} />
                </div>
            </form>
        </>
    )
}

export default SelectBox;